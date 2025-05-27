package Repository;

        import DTOS.FinancialsDTO;
        import com.google.gson.Gson;
        import com.google.gson.reflect.TypeToken;

        import java.io.*;
        import java.lang.reflect.Type;
        import java.util.*;

        public class FinancialRepository {
            private final Gson gson = new Gson();
            private List<FinancialsDTO> financialsList = new ArrayList<>();
            private final String FILE_PATH = "financials.txt";

            public void addFinancial(FinancialsDTO financial) {
                financialsList.add(financial);
                saveToFile();
            }

            public FinancialsDTO getById(String id) {
                for (FinancialsDTO f : financialsList) {
                    if (f.getFinancialId() != null && f.getFinancialId().equals(id)) {
                        return f;
                    }
                }
                return null;
            }

            public List<FinancialsDTO> getByEventId(String eventId) {
                List<FinancialsDTO> result = new ArrayList<>();
                for (FinancialsDTO f : financialsList) {
                    if (f.getEventId().equals(eventId)) {
                        result.add(f);
                    }
                }
                return result;
            }

            public List<FinancialsDTO> getAllFinancials() {
                return new ArrayList<>(financialsList);
            }

            public void saveToFile() {
                try (Writer writer = new FileWriter(FILE_PATH)) {
                    gson.toJson(financialsList, writer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public void loadFromFile() {
                financialsList.clear();
                try (Reader reader = new FileReader(FILE_PATH)) {
                    Type listType = new TypeToken<List<FinancialsDTO>>() {}.getType();
                    List<FinancialsDTO> loadedList = gson.fromJson(reader, listType);
                    if (loadedList != null) {
                        financialsList.addAll(loadedList);
                    }
                } catch (FileNotFoundException e) {
                    // Si el archivo no existe, la lista queda vacía
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }