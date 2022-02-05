package DataAccessLayer.DTOs;

import DataAccessLayer.DalController;

public class DCourse extends DTO{
    public static final String NameColumnName="Name";
    public static final String GeneralInfoColumnName="GeneralInfo";
    private boolean persisted;
    private String name;
    private String generalInfo;

    public DCourse(DalController controller, int id, String name, String generalInfo) {
        super(controller, id);
        this.name = name;
        this.generalInfo = generalInfo;
        persisted = false;
    }

    public String getName() {
        return name;
    }

    public String getGeneralInfo() {
        return generalInfo;
    }
}
