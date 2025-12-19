package SEP1.utils;

import SEP1.model.*;

public class DataInitializer {

  public static ClovervilleModel createDefaultModel() {
    ClovervilleModel model = new ClovervilleModel(200);

    ResidentList residents = model.getResidentList();
    residents.addResident(new Resident("Lucas Merritt","lucas.merritt@mail.com","58210467"));
    residents.addResident(new Resident("Aria Thompson","aria.thompson@mail.com","73918520"));
    residents.addResident(new Resident("Nathan Cole","nathan.cole@mail.com","81462957"));
    residents.addResident(new Resident("Emily Johnson","emily.johnson@mail.com","98765432"));
    residents.addResident(new Resident("Michael Williams","michael.williams@mail.com","34567890"));
    residents.addResident(new Resident("Sarah Taylor","sarah.taylor@mail.com","45678901"));
    residents.addResident(new Resident("Mia Delgado","mia.delgado@mail.com","92375148"));
    residents.addResident(new Resident("Oliver Hayes","oliver.hayes@mail.com","67482951"));
    residents.addResident(new Resident("Zoe Patel","zoe.patel@mail.com","75829164"));
    residents.addResident(new Resident("Ethan Granger","ethan.granger@mail.com","89261475"));
    residents.addResident(new Resident("Layla Rivera","layla.rivera@mail.com","63572019"));
    residents.addResident(new Resident("Henry Lawson","henry.lawson@mail.com","70418526"));
    residents.addResident(new Resident("Ava Monroe","ava.monroe@mail.com","86194725"));
    residents.addResident(new Resident("Jackson Reid","jackson.reid@mail.com","52981647"));
    residents.addResident(new Resident("Liam Brooks","liam.brooks@mail.com","69357184"));
    residents.addResident(new Resident("Chloe Simmons","chloe.simmons@mail.com","81049263"));
    residents.addResident(new Resident("Mason Clark","mason.clark@mail.com","95761482"));
    residents.addResident(new Resident("Sophia Grant","sophia.grant@mail.com","62849157"));
    residents.addResident(new Resident("Leo Bennett","leo.bennett@mail.com","73450916"));
    residents.addResident(new Resident("Nora Carter","nora.carter@mail.com","89572043"));
    residents.addResident(new Resident("Dylan Matthews","dylan.matthews@mail.com","51739824"));
    residents.addResident(new Resident("Isla Parker","isla.parker@mail.com","64028179"));
    residents.addResident(new Resident("Aaron Wolfe","aaron.wolfe@mail.com","78349250"));
    residents.addResident(new Resident("Ruby Flores","ruby.flores@mail.com","94285730"));
    residents.addResident(new Resident("Gabriel Shaw","gabriel.shaw@mail.com","61578324"));
    residents.addResident(new Resident("Stella Morgan","stella.morgan@mail.com","70396412"));
    residents.addResident(new Resident("Julian Price","julian.price@mail.com","87412053"));
    residents.addResident(new Resident("Hazel Kim","hazel.kim@mail.com","59247831"));
    residents.addResident(new Resident("Samuel Ortiz","samuel.ortiz@mail.com","81653074"));
    residents.addResident(new Resident("Violet Hughes","violet.hughes@mail.com","73952018"));
    residents.addResident(new Resident("Adrian Blake","adrian.blake@mail.com","90578421"));
    residents.addResident(new Resident("Maya Chavez","maya.chavez@mail.com","68421957"));
    residents.addResident(new Resident("Ryan Cooper","ryan.cooper@mail.com","72139854"));
    residents.addResident(new Resident("Ella Reed","ella.reed@mail.com","69310247"));
    residents.addResident(new Resident("Benjamin Scott","benjamin.scott@mail.com","80421539"));
    residents.addResident(new Resident("Victoria Hill","victoria.hill@mail.com","65710234"));
    residents.addResident(new Resident("Owen Rogers","owen.rogers@mail.com","73249518"));
    residents.addResident(new Resident("Lily Martinez","lily.martinez@mail.com","91427530"));
    residents.addResident(new Resident("Jacob Hall","jacob.hall@mail.com","82531947"));
    residents.addResident(new Resident("Scarlett Adams","scarlett.adams@mail.com","50192384"));
    residents.addResident(new Resident("Elijah Brooks","elijah.brooks@mail.com","84120597"));
    // -> 42 residents

    GreenActionList gaList = model.getGreenActionList();
    gaList.add(new GreenAction("Use Bicycle for a Week","Use bike instead of car",40,false));
    gaList.add(new GreenAction("Eat Vegan for a Week","Plant-based meals only",35,false));
    gaList.add(new GreenAction("No Plastic Week","Avoid single-use plastic",30,false));
    gaList.add(new GreenAction("Public Transport Week","Only buses/trams/trains",25,false));
    gaList.add(new GreenAction("Home Energy Saver","Reduce electricity",20,false));
    gaList.add(new GreenAction("Plant a Tree","Plant one tree",50,false));
    gaList.add(new GreenAction("Zero Food Waste Week","No food thrown away",45,false));
    gaList.add(new GreenAction("Reusable Bottle Week","No disposable bottles",22,false));
    gaList.add(new GreenAction("Neighborhood Clean-Up","Collect trash outdoors",48,false));
    gaList.add(new GreenAction("Short Showers Week","Max 5 min showers",28,false));

    CommunalTaskList ctList = model.getCommunalTaskList();
    ctList.add(new CommunalTask("Cook for Village","Prepare a communal meal",50));
    ctList.add(new CommunalTask("Clean Village Square","Sweep and remove trash",30));
    ctList.add(new CommunalTask("Maintain Garden","Weed and water plants",35));
    ctList.add(new CommunalTask("Repair Tools","Fix shared tools",25));
    ctList.add(new CommunalTask("Help Elderly","Assist elderly residents",40));
    ctList.add(new CommunalTask("Clean Forest Path","Clear hiking path",28));
    ctList.add(new CommunalTask("Maintain Water Source","Clean village well",48));
    ctList.add(new CommunalTask("Organize Event","Plan community event",32));
    ctList.add(new CommunalTask("Organize Storage","Clean & sort storage",22));
    ctList.add(new CommunalTask("Build Infrastructure","Help with benches/paths",45));

    return model;
  }
}

