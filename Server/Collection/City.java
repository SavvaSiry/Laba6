package com.company.Collection;

import com.company.Commands.Constants;
import com.company.Commands.Info;

import java.io.Serializable;
import java.time.LocalDateTime;

public class City implements Comparable<City>, Serializable {
    transient private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    transient private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long area; //Значение поля должно быть больше 0, Поле не может быть null
    private long population; //Значение поля должно быть больше 0
    private Double metersAboveSeaLevel;
    private Boolean capital; //Поле может быть null
    private Long agglomeration;
    private Climate climate; //Поле не может быть null
    private Human governor; //Поле не может быть null

    public City(){
        int id = Constants.getID();
        if (id != 0) {
            this.setId(id);
            LocalDateTime date = LocalDateTime.now();
            this.setCreationDate(date);
        } else {
            this.setId(id);
            LocalDateTime date = LocalDateTime.now();
            this.setCreationDate(date);
            Info.setCreationDate(date);
        }
    }

        public Integer getId () {
        return id;
    }

        private void setId(Integer id){
        this.id = id;
    }

        public String getName () {
        return name;
    }

        public void setName(String name){
        if (Constants.nullChecker(name)) {
            this.name = name;
        } else Constants.breakComment("City = null, или строка пуста");
    }

        public Coordinates getCoordinates () {
        return coordinates;
    }

        public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }

        public LocalDateTime getCreationDate () {
        return creationDate;
    }

        void setCreationDate (LocalDateTime creationDate){
        this.creationDate = creationDate;
    }

        public Long getArea () {
        return area;
    }

        public void setArea(Long area) {
            if (area > 0 ) {
                this.area = area;
            } else Constants.breakComment("Поле area длжно быть больше нуля");
    }

        public long getPopulation() {
        return population;
    }

        public void setPopulation(long population){

            if (population > 0) {
                this.population = population;
            } else Constants.breakComment("Значение поля Population должно быть больше нуля");
        }

        public Double getMetersAboveSeaLevel () {
        return metersAboveSeaLevel;
    }

        public void setMetersAboveSeaLevel(Double metersAboveSeaLevel){
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }

        public Boolean getCapital () {
        return capital;
    }

        public void setCapital(Boolean capital){
        this.capital = capital;
    }

        public Long getAgglomeration () {
        return agglomeration;
    }

        public void setAgglomeration(Long agglomeration){
        this.agglomeration = agglomeration;
    }

        public Climate getClimate () {
        return climate;
    }

        public void setClimate(Climate climate){
        this.climate = climate;
    }

        public Human getGovernor () {
        return governor;
    }

        public void setGovernor(Human governor){
        this.governor = governor;
    }


    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + " x = " + coordinates.getX() + " || y = " + coordinates.getY() +
                ", creationDate=" + creationDate +
                ", area=" + area +
                ", population=" + population +
                ", metersAboveSeaLevel=" + metersAboveSeaLevel +
                ", capital=" + capital +
                ", agglomeration=" + agglomeration +
                ", climate=" + climate.name() +
                ", governor=" + governor.getName() +
                '}';
    }

    public int compareTo(City city) {
        return Long.compare(city.getPopulation(), this.population);
    }
}