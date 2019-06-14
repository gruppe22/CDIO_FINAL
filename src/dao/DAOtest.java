package dao;

import dto.BrugerDTO;

public class DAOtest {
    public static void main(String[] args) {
        BrugerDTO Kasper = new BrugerDTO(1, "Kasper", "KB", "2510882134", "admin");
        IBrugerDAO dao = new BrugerDAO();


        try {

            BrugerDTO hej = dao.getBruger(1);
            System.out.println(hej.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }}