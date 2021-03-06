package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s; %s; %s; %s; %s; %s; %s; %s; %s\n", contact.getFirstname(),
                    contact.getLastname(),
                    contact.getAddress(),
                    contact.getHomePhone(),
                    contact.getMobilePhone(),
                    contact.getWorkPhone(),
                    contact.getEmail(),
                    contact.getEmail2(),
                    contact.getEmail3()));
        }
        writer.close();
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstname(String.format("Firstname %s", i))
                    .withLastname(String.format("Lastname %s", i))
                    .withAddress(String.format("Address %s", i))
                    .withHomePhone(String.format("787458494%s", i))
                    .withMobilePhone(String.format("4455667%s", i))
                    .withWorkPhone(String.format("1122224444%s", i))
                    .withEmail(String.format("email" + i + "@test.com"))
                    .withEmail2(String.format("email2" + (i+1) + "@test.com"))
                    .withEmail3(String.format("email3" + (i+2) + "@test.com"))
                    .withPhoto(new File(String.format("src/test/resources/ava.jpg"))));
        }
        return contacts;
    }
}
