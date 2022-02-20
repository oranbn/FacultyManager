using FacultyManager.Model;
using System;
using System.Windows.Input;

namespace FacultyManager.ViewModel
{
    public class LoginViewModel : NotifiableObject
    {
        public FacultyController Controller { get; private set; }
        private string _email;
        public string Email
        {
            get => _email;
            set
            {
                this._email = value;
                RaisePropertyChanged("Email");
            }
        }
        private string _password;
        public string Password
        {
            get => _password;
            set
            {
                this._password = value;
                RaisePropertyChanged("Password");
            }
        }
        private string _message;
        public string Message
        {
            get => _message;
            set
            {
                this._message = value;
                RaisePropertyChanged("Message");
            }
        }
        /// <summary>
        /// A method to login
        /// </summary>
        /// <returns>A user model object</returns>
        public UserModel Login()
        {
           
            Message = "";
            try
            {
                return Controller.Login(Email, Password);
            }
            catch (Exception e)
            {
                Message = e.Message;
                return null;
            }
        }
        /// <summary>
        /// A method to register
        /// </summary>
        public void Register()
        {
            Message = "";
            try
            {
                Controller.Register(Email, Password);
                Message = "Registered successfully";
            }
            catch (Exception e)
            {
                Message = e.Message;
            }
        }
        /// <summary>
        /// A method to clear message
        /// </summary>
        public void ClearMessage()
        {
            Message = "";
        }
        /// <summary>
        /// Constructor
        /// </summary>
        public LoginViewModel()
        {
            this.Controller = new BackendController();
        }
    }
}