// Load JSON data and hydrate pages
fetch('http://localhost:8080/cloverville.json?ts=' + Date.now(), { cache: 'no-store' })
  .then(response => response.json())
  .then(data => {
    loadGreenActions(data);
    loadTradeOffers(data);
    loadCommunityPoints(data);
  })
  .catch(err => console.error('Error loading JSON:', err));

// Helper to collect completed green actions from residents without duplicates
function getCompletedGreenActions(data) {
  const residents = data.residentList?.residents || [];
  const completed = [];
  const seen = new Set();
  residents.forEach(r => {
    (r.greenActions || []).forEach(a => {
      const key = `${a.name}|${a.description || ''}|${a.pointsGiven}`;
      if (seen.has(key)) return;
      seen.add(key);
      completed.push({
        action: a.name,
        description: a.description || 'Green action completed.',
        points: a.pointsGiven
      });
    });
  });
  return completed;
}

// --- GREEN ACTIONS PAGE (completed inspirational actions) ---
function loadGreenActions(data) {
  const actionsGrid = document.querySelector('.actions-grid');
  if (!actionsGrid) return;

  const completed = getCompletedGreenActions(data);
  actionsGrid.innerHTML = '';

  if (completed.length === 0) {
    actionsGrid.innerHTML = '<p>No green actions have been completed yet.</p>';
    return;
  }

  completed.forEach(item => {
    const card = document.createElement('div');
    card.classList.add('action-card');
    card.innerHTML = `
      <h3>${item.action}</h3>
      <p>${item.description}</p>
      <p>Points: ${item.points}</p>
    `;
    actionsGrid.appendChild(card);
  });
}

// --- TRADE OFFERS PAGE ---
function loadTradeOffers(data) {
  const offersGrid = document.querySelector('.offers-grid');
  if (!offersGrid) return;

  offersGrid.innerHTML = '';

  const offersToRender = data.tradeOfferList?.offers || [];

  if (offersToRender.length === 0) {
    offersGrid.innerHTML = '<p>No trade offers available currently.</p>';
    return;
  }

  offersToRender.forEach(offer => {
    const card = document.createElement('div');
    card.classList.add('offer-card');

    const buyerText = offer.offerBought && offer.offerBought.length > 0
      ? `Bought by: ${offer.offerBought}`
      : 'Not bought yet';

    card.innerHTML = `
      <h3>${offer.name}</h3>
      <p>Provider: ${offer.offerMade}</p>
      <p>${offer.description}</p>
      <p>Cost: ${offer.pointCost} pts</p>
      <p>${buyerText}</p>
    `;

    offersGrid.appendChild(card);
  });
}

// --- COMMUNITY POINTS PAGE ---
function loadCommunityPoints(data) {
  const communityPointsEl = document.getElementById('communityPoints');
  if (!communityPointsEl) return;

  communityPointsEl.textContent = data.communalPool.totalPoints;

  const tasksBox = document.querySelector('.tasks-box ul');
  if (tasksBox) {
    tasksBox.innerHTML = '';
    (data.communalTaskList?.tasks || []).forEach(task => {
      const li = document.createElement('li');
      li.textContent = `${task.name} - ${task.description} (${task.points} pts)`;
      tasksBox.appendChild(li);
    });
  }

  const actionsBox = document.querySelector('.actions-box ul');
  if (actionsBox) {
    actionsBox.innerHTML = '';
    const available = data.greenActionList?.actions || [];
    if (available.length === 0) {
      const li = document.createElement('li');
      li.textContent = 'No green actions available right now.';
      actionsBox.appendChild(li);
    } else {
      available.forEach(action => {
        const li = document.createElement('li');
        li.textContent = `${action.name} (${action.pointsGiven} pts)`;
        actionsBox.appendChild(li);
      });
    }
  }

  const rewardsList = document.querySelector('.rewards-list');
  if (rewardsList) {
    rewardsList.innerHTML = '';
    const rewards = [
      { label: 'Community Picnic', points: 100 },
      { label: 'Tree Planting Day', points: 180 },
      { label: 'Tool Library Upgrade', points: 240 }
    ];
    rewards.forEach(item => {
      const li = document.createElement('li');
      li.innerHTML = `
        <span class="reward-name">${item.label}</span>
        <span class="reward-points">${item.points} pts</span>
      `;
      rewardsList.appendChild(li);
    });
  }
}
