package Model;

import javafx.scene.control.Alert;

public abstract class Part {

        private int id;
        private String name;
        private double price;
        private int stock;
        private int min;
        private int max;

        public Part(){

        }

        public Part(int id, String name, double price, int stock, int min, int max) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.stock = stock;
            this.min = min;
            this.max = max;
        }
            //Set methods to assign values

        public void setId(int id) {
            this.id = id;
            }
        public void setName(String name) {
            this.name = name;
        }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }



            //Get methods to return values

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public String foundError;

    public Alert PartScreenAlert;



    //Validity check method for adding and modifying Parts

    public boolean notValid(String name, Double price, Integer stock, Integer min, Integer max) {
        /**
         * first portion checks to make sure a name was input
         */
        if(name.isBlank()){
            foundError = "No name input";
            return true;
        }
        /**
         * second portion checks to make name contains letters only
         */
        for (int i = 0; i < name.length(); i++) {
            char check = name.toLowerCase().charAt(i);
                if (!(check >= 'a' && check <= 'z')) {
                    foundError = "Name must only contain letters";
                    return true;
                }
        }
        /**
         * checks to make sure price is not negative and greater than zero;
         */
        if (price < 0) {
            foundError = "Price cannot be negative";
            return true;
        }
        /**
         * checks to make sure inventory, max and min levels are correct
         */
        if (!(stock >= min && stock <= max)) {
            foundError = "Inventory Level must be greater than or equal to minimum and less than or equal to maximum.";
            return true;
        }


        //return line
        return false;
    }






}

