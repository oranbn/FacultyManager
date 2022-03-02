using System.Collections.ObjectModel;
using System.Collections.Specialized;
using System.Linq;
using System.Windows;

namespace FacultyManager.Model
{
    public class FacultyManagerModel : NotifiableModelObject
    {
        private readonly UserModel user;
        public ObservableCollection<CourseModel> Courses { get; set; }
        /// <summary>
        /// A FacultyManager model constructor
        /// </summary>
        /// <param name="controller">FacultyController object</param>
        /// <param name="courses">ObservableCollection<CourseModel> obecjt</param>
        private FacultyManagerModel(FacultyController controller, ObservableCollection<CourseModel> courses) : base(controller)
        {
            Courses = courses;
            Courses.CollectionChanged += HandleChange;
        }
        /// <summary>
        /// A FacultyManager model constructor
        /// </summary>
        /// <param name="controller">FacultyController object</param>
        /// <param name="user">UserModel model</param>
        public FacultyManagerModel(FacultyController controller, UserModel user) : base(controller)
        {
            this.user = user;
            Courses = new ObservableCollection<CourseModel>(controller.GetAllCourses(user));
            Courses.CollectionChanged += HandleChange;
        }

        /// <summary>
        /// A method to remove course
        /// </summary>
        /// <param name="t">CourseModel object</param>
        public void RemoveCourse(CourseModel t)
        {
            Courses.Remove(t);
        }
        /// <summary>
        /// A method to handle chagnes in the courses list
        /// </summary>
        /// <param name="sender">An object</param>
        /// <param name="e">NotifyCollectionChangedEventArgs object</param>
        private void HandleChange(object sender, NotifyCollectionChangedEventArgs e)
        {
            if (e.Action == NotifyCollectionChangedAction.Remove)
            {
                foreach (CourseModel y in e.OldItems)
                {
                   // send delete request to the server through FacultyController method
                }

            }
        }

        public override void Execute()
        {
            throw new System.NotImplementedException();
        }
    }
}
