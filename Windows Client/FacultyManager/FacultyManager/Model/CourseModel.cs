using System.Collections.Generic;

namespace FacultyManager.Model
{
    public class CourseModel : NotifiableModelObject
    {
        private int _id;
        public int Id
        {
            get => _id;
            set
            {
                this._id = value;
                RaisePropertyChanged("Id");
            }
        }
        private string _courseName;
        public string CourseName
        {
            get => _courseName;
            set
            {
                this._courseName = value;
                RaisePropertyChanged("CourseName");
            }
        }
        private string _generalInfo;
        public string GeneralInfo
        {
            get => _generalInfo;
            set
            {
                this._generalInfo = value;
                RaisePropertyChanged("GeneralInfo");
            }
        }
        private List<string> _members;
        public List<string> Members
        {
            get => _members;
            set
            {
                this._members = value;
                RaisePropertyChanged("Members");
            }
        }
        /// <summary>
        /// Constructor for the course model
        /// </summary>
        /// <param name="controller">A faculty controller object</param>
        /// <param name="id">The id of the board</param>
        /// <param name="courseName">The course name</param>
        /// <param name="generalInfo">The general info</param>
        public CourseModel(int id, string courseName, string generalInfo) : base(null)
        {
            Id = id;
            CourseName = courseName;
            GeneralInfo = generalInfo;
        }

        public CourseModel((int Id, string courseName, string generalInfo) course) : this(course.Id, course.courseName, course.generalInfo) { }

        public override void Execute(object parameter)
        {
            throw new System.NotImplementedException();
        }
    }
}
