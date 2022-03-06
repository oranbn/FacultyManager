using FacultyManager.Model;
using System;
using System.Windows;
using System.Windows.Input;
using FacultyManager.Commands;
using FacultyManager.Model.Operations;
using FacultyManager.Model.Operations.ServerResponse;
using FacultyManager.Service;
using FacultyManager.Stores;

namespace FacultyManager.ViewModel
{
    public class LoginViewModel : NotifiableObject
    {
        public ICommand LoginCommand { get; }
        public ICommand LoginSuccessCommand { get; }
        public ICommand CloseLoginCommand { get; }
        public ICommand ForgotPasswordCommand { get; }
        public ICommand ActivationCommand { get; }
        private readonly FacultyController _controller;
        private readonly AccountStore _account;
        private string _email;
        public string Email
        {
            get => _email;
            set
            {
                _email = value;
                _message = "";
                RaisePropertyChanged("Message");
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
                _message = "";
                RaisePropertyChanged("Message");
                RaisePropertyChanged("Password");
            }
        }
        private string _message;
        public string Message
        {
            get => _message;
            set
            {
                _message = value;
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
            ServerOperation response =  _controller.Login(Email, Password);
            switch (response.getOpCode())
            {
                case 2:
                    Message = ((MessageResponse)response).getOptional();
                    break;
                case 3:
                    _account.CurrentAccount = new Account(((AccountResponse)response).Email,((AccountResponse)response).FirstName,((AccountResponse)response).LastName,((AccountResponse)response).IdNumber,((AccountResponse)response).PhoneNumber,((AccountResponse)response).Birthday ,((AccountResponse)response).Permission, ((AccountResponse)response).IsApproved);
                    if(_account.CurrentAccount.IsApproved)
                        LoginSuccessCommand.Execute(null);
                    else
                        ActivationCommand.Execute(null);
                    break;
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
        public LoginViewModel(FacultyController facultyController, AccountStore accountStore, INavigationService homeNavigationService, INavigationService closeNavigationService, INavigationService activationNavigationService, INavigationService forgotPasswordNavigationService)
        {
            _controller = facultyController;
            _account = accountStore;
            LoginCommand = new GenericCommand(this);
            LoginSuccessCommand = new NavigateCommand(homeNavigationService);
            CloseLoginCommand = new NavigateCommand(closeNavigationService);
            ActivationCommand = new NavigateCommand(activationNavigationService);
            ForgotPasswordCommand = new NavigateCommand(forgotPasswordNavigationService);
        }

        public override void Execute(object parameter)
        {
            Login();
        }
    }
}