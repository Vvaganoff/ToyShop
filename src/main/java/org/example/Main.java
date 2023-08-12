package org.example;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Toy1> toys = new ArrayList<Toy1>();
        HashMap<String, Toy1> winnersMap = new HashMap<>();
        toys.add(Toy1.NewToy(scanner, toys.size()));
        String choice = "0";
        while (!choice.equals("10")) {
            System.out.println("""
                    Выберите дальнейшее действие:
                    1 - Ввести новую игрушку
                    2 - Рассчитать частоту выпадания
                    3 - Вывести список игрушек
                    4 - Изменить игрушку
                    5 - Удалить игрушку
                    6 - Провести розыгрыш
                    7 - Просмотреть список игрушек к выдаче
                    8 - Выдать игрушку
                    9 - Просмотреть список выданных игрушек
                    10- Выход""");
            choice = scanner.next();
            switch (choice) {
                case "1": {
                    toys.add(Toy1.NewToy(scanner, toys.size()));
                    break;
                }
                case "2": {
                    int sum = 0;
                    for (Toy1 toy : toys
                    ) {
                        sum = sum + toy.count;
                    }
                    for (Toy1 toy : toys
                    ) {
                        toy.toyfrequency(sum);
                    }
                    break;
                }
                case "3": {
                    PrintArray(toys);
                    break;
                }
                case "4": {
                    PrintArray(toys);
                    System.out.println("Введите Id игрушки для изменения: ");
                    int toyId = Integer.parseInt(scanner.next());
                    Toy1 toyToChange = FindToy(toys, toyId);
                    if (toyToChange.getToyId() != 9999) {
                        toyToChange.toString();
                        System.out.println("""
                                1 - Название
                                2 - Количество
                                3 - Частоту выпадания""");
                        System.out.println("Введите то, что хотели бы изменить:");
                        String choiceToChange = scanner.next();
                        switch (choiceToChange) {
                            case "1": {
                                System.out.println("Введите новое наименование:");
                                String nameToChange = scanner.next();
                                toyToChange.setToyName(nameToChange);
                                break;
                            }
                            case "2": {
                                System.out.println("Введите новое количество");
                                int countToChange = Integer.parseInt(scanner.next());
                                toyToChange.setCount(countToChange);
                                break;
                            }
                            case "3": {
                                System.out.println("Введите новую частоту:");
                                Double frequencyToChange = Double.parseDouble(scanner.next());
                                toyToChange.setFrequency(frequencyToChange);
                                break;
                            }
                            default:
                                System.out.println("Что-то вы не то ввели...");
                                break;
                        }
                    } else {
                        System.out.println("Такой id не найден");
                        break;
                    }
                break;
                }
                case "5": {
                    PrintArray(toys);
                    System.out.println("Введите Id игрушки для удаления: ");
                    int toyId = Integer.parseInt(scanner.next());
                    Toy1 toyToDelete = FindToy(toys, toyId);
                    if (toyToDelete.getToyId() != 9999) {
                        toys.remove(toyToDelete);
                        PrintArray(toys);
                    } else {
                        System.out.println("Такой id не найден");
                        break;
                    }
                }
                case "6": {
                    ArrayList<String> playerNames = new ArrayList<String>();
                    String choiceNames = "y";
                    while (choiceNames.equals("y")){
                        System.out.println("Введите имя игрока: ");
                        playerNames.add(scanner.next());
                        System.out.println("Хотите ввеси ещё игрока? (у/n)");
                        choiceNames = scanner.next();
                    }
                    System.out.println("Введите количество победителей:");
                    int winnersQuantity = Integer.parseInt(scanner.next());
                    winnersMap = ToyRaffle(playerNames, toys, winnersQuantity);
                    PrintWinners(winnersMap);
                    break;
                }
                case "7": {
                    PrintWinners(winnersMap);
                    break;
                }
                case "8": {
                    PrintWinners(winnersMap);
                    String file = "PrizeGiven.txt";
                    GivePrize(file, winnersMap);
                    break;
                }
                case "9": {
                    String file = "PrizeGiven.txt";
                    PrintWinnersFromFile(file);
                    break;
                }
                default: break;
            }
        }

    }

    public static Toy1 FindToy(ArrayList<Toy1> list, int id) {

        for (Toy1 toy : list
        ) {
            if (toy.toyId == id) {
                return toy;
            }
        }

        return new Toy1(9999, "exeption", 0);
    }

    public static void PrintArray(ArrayList<Toy1> toys) {
        for (Toy1 toy : toys
        ) {
            System.out.println(toy.toString());
        }
    }

    public static HashMap <String, Toy1> ToyRaffle(ArrayList<String>playerNames, ArrayList<Toy1> toys, int winnersquantity){
        Random rndm = new Random();
        HashMap<String, Toy1> resultHashMap = new HashMap<>();
        ArrayList<String> winnersArray = new ArrayList<>();
        ArrayList<Integer> toysIdToDelete = new ArrayList<Integer>();
        if (playerNames.size()>winnersquantity){
            for (int i = 0; i < winnersquantity; i++) {
                winnersArray.add(playerNames.get(rndm.nextInt(0, playerNames.size()-1)));
            }
        } else {winnersArray = playerNames;}
        Comparator<Toy1> comparator = new ToyComparator();
        toys.sort(comparator);
        int rndmNumber = rndm.nextInt(100 - (int)toys.get(toys.size()-1).frequency, 100 - (int)toys.get(0).frequency);
        int winnerIndx = 0;
        while (winnerIndx < winnersArray.size()) {
            for (Toy1 item : toys
            ) {
                if ((int) item.frequency + rndmNumber >= 100) {
                    resultHashMap.put(winnersArray.get(winnerIndx), item);
                    if (item.getCount() - 1 > 0) {
                        item.setCount(item.getCount() - 1);
                    } else {
                        toysIdToDelete.add(item.toyId);
                    }
                }
            }
            winnerIndx++;
        }
        for (int toyId:toysIdToDelete
             ) {
            Toy1 toyToDelete = FindToy(toys, toyId);
            if (toyToDelete.getToyId() != 9999) {
                toys.remove(toyToDelete);
                PrintArray(toys);
            }
        }
        return resultHashMap;
    }

    public static void PrintWinners(HashMap<String, Toy1> winners){
        for (String name: winners.keySet()
             ) {
            System.out.println(" Победитель: " + name + ", " + "игрушка: " + winners.get(name).getToyName());
        }
    }

    public static void GivePrize(String file, HashMap<String, Toy1> winnersMap) throws IOException {
        ArrayList<String> stringArrayList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String winnerName = "";
        System.out.println("Введите имя человека, который пришёл за призом (для выхода введите пустую строку):");
        winnerName = scanner.nextLine();
        while (!winnerName.equals("")){
            if (winnersMap.containsKey(winnerName)){
                stringArrayList.add(winnerName + " - " + winnersMap.get(winnerName).getToyName());
            } else System.out.println("Этого человека нет в числе выигравших.");
            System.out.println("Введите имя человека, который пришёл за призом (для выхода введите пустую строку):");
            winnerName = scanner.nextLine();
        }
        WorkWithFile.WriteFile(file, stringArrayList);
    }
    public static void PrintWinnersFromFile(String file) throws IOException {
        ArrayList<String> stringArrayList;
        stringArrayList = WorkWithFile.ReadFile(file);
        for (String str:stringArrayList
             ) {
            System.out.println(str);
        }
    }
}
