using System;
using System.Collections.ObjectModel;
using System.Collections.Specialized;
using System.Linq;
using System.Windows;
using FacultyManager.Model.Operations;
using FacultyManager.Model.Operations.ServerResponse;
using FacultyManager.Stores;

namespace FacultyManager.Model
{
    public class CoursesModel : NotifiableModelObject
    {
        public ObservableCollection<CourseModel> Courses { get; set; }
        private readonly Account _account;
        /// <summary>
        /// A courses model constructor
        /// </summary>
        /// <param name="controller">FacultyController object</param>
        /// <param name="courses">ObservableCollection<CourseModel> object</param>
        private CoursesModel(FacultyController controller, ObservableCollection<CourseModel> courses) : base(controller)
        {
            Courses = courses;
            Courses.CollectionChanged += HandleChange;
        }
        /// <summary>
        /// A kanban model constructor
        /// </summary>
        /// <param name="controller">BackendController object</param>
        /// <param name="user">UserModel model</param>
        public CoursesModel(FacultyController controller, AccountStore account) : base(controller)
        {
            _account = account.CurrentAccount;
            if (account.IsLoggedIn)
            {
                ServerOperation response = controller.GetCourses();
                if (response.getOpCode() == 4)
                    Courses = new ObservableCollection<CourseModel>((((CoursestResponse) response).Courses));
            }
            else
                Courses = new ObservableCollection<CourseModel>();
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
                    Controller.RemoveCourse(y.Id);                    
                }

            }
        }

        public override void Execute(object parameter)
        {
            throw new System.NotImplementedException();
        }
    }
}