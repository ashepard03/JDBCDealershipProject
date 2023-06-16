package org.yearup.cardealership;

import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {
    private String fileName;

    public ContractDataManager(String fileName) {
        this.fileName = fileName;
    }

    public void saveContract(Contract contract) {
        try (FileWriter writer = new FileWriter(fileName, true)) {

            try {
                writer.write(contract.getPersistenceString()+"\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Contract saved successfully.");

        } catch (IOException e) {

            System.err.println("Error saving contract: ");


        }
    }

}
