using FacultyManager.Model;
using System;
using System.Windows.Input;
using FacultyManager.Commands;
using FacultyManager.Stores;

namespace FacultyManager.ViewModel
{
    public class LoginViewModel : NotifiableObject
    {
        public ICommand NavigateRegisterCommand { get; }
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
        public void Login()
        {
            Message = "";
            try
            {
                Controller.Login(Email, Password);
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
        public LoginViewModel(NavigationStore navigationStore)
        {
            this.Controller = new FacultyController();
            NavigateRegisterCommand = new NavigateCommand<RegisterViewModel>(navigationStore, () => new RegisterViewModel(navigationStore));
        }
    }
}