package DataAccessLayer.DTOs;

import DataAccessLayer.DalController;

public abstract class DTO {
    public final String IDColumnName = "ID";
    private int id;
    protected DalController controller;
    public DTO(DalController controller, int id)
    {
        this.controller = controller;
        this.id = id;
    }
}
