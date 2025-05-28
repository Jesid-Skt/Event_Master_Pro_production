package Ui;

import Ui.GuiPackageMainMenu.GuiMainMenu;
import Repository.VenueRepository;
import Repository.TicketRepository;
import Repository.EventRepository;
import Repository.AttendeeRepository;
import Repository.ArtistRepository;
import Repository.AccessControlRepository;
import Repository.FinancialRepository;
import Repository.TechnicalFeatureRepository;



// Importa otros repositorios según tu proyecto

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PruebasGUIS {
    public static void main(String[] args) {
        try {
            // Inicializa los repositorios y crea los archivos si no existen
            VenueRepository venueRepository = new VenueRepository();
            venueRepository.saveToFile();

            TicketRepository ticketRepository = new TicketRepository();
            ticketRepository.saveToFile();

            EventRepository eventRepository = new EventRepository();
            eventRepository.saveToFile();

            AttendeeRepository attendeeRepository = new AttendeeRepository();
            attendeeRepository.saveAll(new ArrayList<>());

            TechnicalFeatureRepository technicalFeatureRepository = new TechnicalFeatureRepository();
            technicalFeatureRepository.saveToFile();

            ArtistRepository artistRepository = new ArtistRepository();
            artistRepository.saveToFile();

            AccessControlRepository accessControlRepository = new AccessControlRepository();
            accessControlRepository.saveToFile(new HashMap<>());

            FinancialRepository financialRepository = new FinancialRepository();
            financialRepository.saveToFile();




            // Repite para otros repositorios que manejen archivos

            // Crea la ventana principal
            JFrame frame = new JFrame("Main Menu");
            GuiMainMenu guiMainMenu = new GuiMainMenu();
            frame.setContentPane(guiMainMenu.getPanelPrincipalMainMenu());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al crear la ventana");
        }
    }
}
