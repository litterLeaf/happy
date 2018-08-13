package com.banda.daogenerator;


import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;
import org.greenrobot.greendao.generator.ToMany;

public class ExampleDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.yinshan.happycash.dao");
//        addWifiPoint(schema);
//        addWifiRecord(schema);
        addLocation(schema);
        addContact(schema);
        addEvent(schema);
        addCallLog(schema);
        addSms(schema);
        new DaoGenerator().generateAll(schema, "../happy-cash-android/app/src/main/java");
    }

    private static void addNote(Schema schema) {
        Entity note = schema.addEntity("Note");
        note.addIdProperty().autoincrement();
        note.addStringProperty("text").notNull();
        note.addStringProperty("comment");
        note.addDateProperty("date");
    }

    private static void addCustomerOrder(Schema schema) {
        Entity customer = schema.addEntity("Customer");
        customer.addIdProperty();
        customer.addStringProperty("name").notNull();

        Entity order = schema.addEntity("Order");
        order.setTableName("ORDERS");
        order.addIdProperty();
        Property orderDate = order.addDateProperty("date").getProperty();
        Property customerId = order.addLongProperty("customerId").notNull().getProperty();
        order.addToOne(customer, customerId);

        ToMany customerToOrders = customer.addToMany(order, customerId);
        customerToOrders.setName("orders");
        customerToOrders.orderAsc(orderDate);

    }

    private static void addLocation(Schema schema) {
        Entity location = schema.addEntity("Location");
        location.addIdProperty().autoincrement();
        location.addIntProperty("locType");
        location.addDoubleProperty("latitude");
        location.addDoubleProperty("longitude");
        location.addDoubleProperty("altitude");
        location.addStringProperty("dateTime");
    }

    private static void addContact(Schema schema) {
        Entity contact = schema.addEntity("Contact");
        contact.addIdProperty().autoincrement();
        contact.addLongProperty("contactId");
        contact.addStringProperty("name");
        contact.addStringProperty("phone");
        contact.addStringProperty("email");
        contact.addStringProperty("updateTime");
        contact.addIntProperty("state");
        contact.addStringProperty("accountType");
        contact.addIntProperty("flag");
    }

    private static void addEvent(Schema schema) {
        Entity event = schema.addEntity("Event");
        event.addIdProperty().autoincrement();
        event.addStringProperty("eventId");
        event.addStringProperty("eventTime");
    }

    private static void addCallLog(Schema schema) {
        Entity callLog = schema.addEntity("CallLog");
        callLog.addIdProperty().autoincrement();
        callLog.addStringProperty("name");
        callLog.addStringProperty("number").notNull();
        callLog.addIntProperty("type");
        callLog.addStringProperty("date");
        callLog.addLongProperty("duration");
        callLog.addIntProperty("state");
        callLog.addIntProperty("flag");
    }

    private static void addSms(Schema schema) {
        Entity message = schema.addEntity("Message");
        message.addIdProperty().autoincrement();
        message.addIntProperty("messageId");
        message.addIntProperty("type");
        message.addIntProperty("threadId");
        message.addStringProperty("address");
        message.addStringProperty("date");
        message.addStringProperty("date_sent");
        message.addStringProperty("subject");
        message.addStringProperty("body");
        message.addIntProperty("state");
        message.addIntProperty("flag");
    }
}
