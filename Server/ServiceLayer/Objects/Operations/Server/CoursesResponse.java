package ServiceLayer.Objects.Operations.Server;

import BusinessLayer.Course;
import ServiceLayer.Objects.ServerOperation;
import ServiceLayer.Protocol;

import java.util.List;

public class CoursesResponse extends ServerOperation {
    List<Course> courseList;

    public CoursesResponse(short opCode, List<Course> courseList) {
        super(opCode);
        this.courseList = courseList;
    }


    public byte[] encode() {
        int size=3;
        for(Course course : courseList) {
            size += course.getName().length()+course.getGeneralInfo().length() + 2;
        }
        byte[] bytes = new byte[size+1];
        bytes[0] = (byte)((getOpCode() >> 8) & 0xFF);
        bytes[1] = (byte)(getOpCode() & 0xFF);
        int index = 2;
        for(Course course : courseList) {
            index = AddStringToByteArray(course.getName(), bytes, index);
            bytes[index++] = 0;
            index = AddStringToByteArray(course.getGeneralInfo(), bytes, index);
            bytes[index++] = 0;
        }
        bytes[index] = (byte)';';
        return bytes;
    }

    @Override
    public void execute(Protocol protocol) {

    }
}
