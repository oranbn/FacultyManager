using System;
using System.Collections.Generic;

namespace FacultyManager.Model
{
    public class UserModel : NotifiableModelObject
    {
        private string _email;
        public string Email
        {
            get => _email;
            set
            {
                _email = value;
                RaisePropertyChanged("Email");
            }
        }
        private string _password;
        public string Password
        {
            get => _password;
            set
            {
                _password = value;
                RaisePropertyChanged("Password");
            }
        }
        /// <summary>
        /// A constructor for the user model
        /// </summary>
        /// <param name="controller">BackendController object</param>
        /// <param name="email">The email of the user</param>
        /// <param name="password">The passwrod of the user</param>
        public UserModel(FacultyController controller, string email, string password) : base(controller)
        {
            this.Email = email;
            this.Password = password;
        }
        /// <summary>
        /// A getter to receive a kanban model obeject
        /// </summary>
        /// <returns>A kanban model object</returns>
        public FacultyManagerModel GetFacultyManagerModel()
        {
            return new FacultyManagerModel(Controller, this);
        }
    }
}
