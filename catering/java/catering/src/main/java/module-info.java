module catering {
    requires java.sql;
    requires com.google.gson;
    exports catering;

    opens catering.businesslogic.turn to com.google.gson;
    opens catering.businesslogic.assignment to com.google.gson;
    opens catering.businesslogic.user to com.google.gson;
    opens catering.businesslogic.recipe to com.google.gson;
    opens catering.businesslogic.menu to com.google.gson;
    opens catering.businesslogic.event to com.google.gson;
}
