package DataAccessLayer.DTOs;

import DataAccessLayer.DChatMessageController;
import DataAccessLayer.DCourseController;
import DataAccessLayer.DalController;

public class DCourse extends DTO{
    public static final String NameColumnName="Name";
    public static final String GeneralInfoColumnName="GeneralInfo";
    private boolean persisted;
    private String name;
    private String generalInfo;

    public DCourse(int id, String name, String generalInfo) {
        super(new DCourseController(), id);
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
    public void insert()
    {
        if(!persisted)
        {
            ((DCourseController)controller).insert(this);
            persisted = true;
        }
    }
    public void setName(String name) {
        this.name = name;
        if(persisted)
        {
            controller.update(getId(), NameColumnName, name);
        }
    }
    public void setGeneralInfo(String generalInfo) {
        this.generalInfo = generalInfo;
        if(persisted)
        {
            controller.update(getId(), GeneralInfoColumnName, generalInfo);
        }
    }
}
