using FacultyManager.Model;
using System;
using System.Windows;
using System.Windows.Input;
using FacultyManager.Commands;
using FacultyManager.Model.Operations;
using FacultyManager.Model.Operations.ServerResponse;
using FacultyManager.Service;
using FacultyManager.Stores;
using FacultyManager.View;

namespace FacultyManager.ViewModel
{
    public class ForgotPasswordViewModel : NotifiableObject
    {
        public ICommand ForgotPasswordCommand { get; }
        private readonly FacultyController _controller;
        public ICommand NavigateCommand { get; }
        private bool _isEmailSent;
        public bool IsEmailSent
        {
            get => _isEmailSent;
            set
            {
                _isEmailSent = value;
                RaisePropertyChanged("IsEmailSent");
            }
        }
        private bool _showResetCodeFrame;
        public bool ShowResetCodeFrame
        {
            get => _showResetCodeFrame;
            set
            {
                _showResetCodeFrame = value;
                RaisePropertyChanged("ShowResetCodeFrame");
            }
        }
        private bool _resetPasswordFrame;
        public bool ResetPasswordFrame
        {
            get => _resetPasswordFrame;
            set
            {
                _resetPasswordFrame = value;
                RaisePropertyChanged("ResetPasswordFrame");
            }
        }
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
        private string _forgotPasswordCode;
        public string ForgotPasswordCode
        {
            get => _forgotPasswordCode;
            set
            {
                _forgotPasswordCode = value;
                _message = "";
                RaisePropertyChanged("Message");
                RaisePropertyChanged("ForgotPasswordCode");
            }
        }
        private string _newPassword;
        public string NewPassword
        {
            get => _newPassword;
            set
            {
                _newPassword = value;
                _message = "";
                RaisePropertyChanged("Message");
                RaisePropertyChanged("NewPassword");
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
        /// A method to send forgot password email
        /// </summary>
        private void ForgotPassword()
        {
            Message = "";
            ServerOperation response =  _controller.ForgotPassword(Email);
            switch (response.getOpCode())
            {
                case 1:
                    IsEmailSent = false;
                    ShowResetCodeFrame = true;
                    break;
                case 2:
                    Message = ((MessageResponse) response).getOptional();
                    break;
            }
        }
        private void ForgotCode()
        {
            Message = "";
            ServerOperation response =  _controller.ForgotPasswordCode(ForgotPasswordCode, Email);
            switch (response.getOpCode())
            {
                case 1:
                    ShowResetCodeFrame = false;
                    ResetPasswordFrame = true;
                    break;
                case 2:
                    Message = ((MessageResponse) response).getOptional();
                    break;
            }
        }
        private void ResetPassword()
        {
            Message = "";
            ServerOperation response = _controller.ResetPassword(NewPassword, Email);
            switch (response.getOpCode())
            {
                case 1:
                    MessageBox.Show("Password Changed Successfully");
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
        public ForgotPasswordViewModel(FacultyController facultyController, INavigationService closeModalService)
        {
            _controller = facultyController;
            ForgotPasswordCommand = new GenericCommand(this);
            NavigateCommand = new NavigateCommand(closeModalService);
            _isEmailSent = true;
            _showResetCodeFrame = false;
            _resetPasswordFrame = false;
        }

        public override void Execute(object parameter)
        {
            if (_isEmailSent)
                ForgotPassword();
            else if(_showResetCodeFrame)
                ForgotCode();
            else
                ResetPassword();
        }
    }
}