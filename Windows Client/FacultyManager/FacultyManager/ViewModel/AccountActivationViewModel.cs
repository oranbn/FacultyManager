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
    public class AccountActivationViewModel : NotifiableObject
    {
        public ICommand ActivateCommand { get; }
        private readonly FacultyController _controller;
        public ICommand NavigateCommand { get; }
        private string _activationCode;
        public string ActivationCode
        {
            get => _activationCode;
            set
            {
                _activationCode = value;
                _message = "";
                RaisePropertyChanged("Message");
                RaisePropertyChanged("ActivationCode");
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
        public void Activate()
        {
            Message = "";
            ServerOperation response =  _controller.ActivateAccount(ActivationCode);
            switch (response.getOpCode())
            {
                case 1:
                    NavigateCommand.Execute(null);
                    break;
                case 2:
                    Message = ((MessageResponse) response).getOptional();
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
        public AccountActivationViewModel(FacultyController facultyController, INavigationService homeNavigationService)
        {
            _controller = facultyController;
            ActivateCommand = new GenericCommand(this);
            NavigateCommand = new NavigateCommand(homeNavigationService);
        }

        public override void Execute(object parameter)
        {
            Activate();
        }
    }
}