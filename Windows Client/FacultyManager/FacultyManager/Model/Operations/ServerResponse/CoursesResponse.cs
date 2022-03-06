using System;
using System.Collections.Generic;

namespace FacultyManager.Model.Operations.ServerResponse
{
    public class CoursestResponse : ServerOperation
    {       
        public string CourseName => _courseName;

        public string GeneralInfo => _generalInfo;

        public List<CourseModel> Courses => _courses;
        
        private string _courseName;
        private string _generalInfo;
        private int id = 0;
        private readonly List<CourseModel> _courses;
        public CoursestResponse(short opCode) : base(opCode)
        {
            _courseName = "";
            _generalInfo = "";
            _courses = new List<CourseModel>();
        }

        public override bool pushByte(byte nextByte)
        {
            if(nextByte == ';')
                return true;
            if(nextByte==(byte)0)
            {
                if(_courseName.Equals(""))
                    _courseName = bytesToString();
                else if (_generalInfo.Equals(""))
                {
                    _generalInfo = bytesToString();
                    _courses.Add(new CourseModel(id++, _courseName, _generalInfo));
                    _courseName = "";
                    _generalInfo = "";
                }
            }
            else
                pushNextByte(nextByte);
            return false;
        }
    }
}