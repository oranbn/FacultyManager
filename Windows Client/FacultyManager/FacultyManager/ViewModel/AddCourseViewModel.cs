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
    public class AddCourseViewModel : NotifiableObject
    {
        public ICommand CloseCommand { get; }
        public ICommand AddCourseCommand { get; }

        private readonly FacultyController _controller;
        private readonly AccountStore _account;
        private string _courseName;
        public string CourseName
        {
            get => _courseName;
            set
            {
                _courseName = value;
                _message = "";
                RaisePropertyChanged("Message");
                RaisePropertyChanged("CourseName");
            }
        }
        private string _generalInfo;
        public string GeneralInfo
        {
            get => _generalInfo;
            set
            {
                _generalInfo = value;
                _message = "";
                RaisePropertyChanged("Message");
                RaisePropertyChanged("GeneralInfo");
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
        public void AddCourse()
        {
            Message = "";
            ServerOperation response =  _controller.CreateCourse(CourseName, GeneralInfo);
            switch (response.getOpCode())
            {
                case 1:
                    CloseCommand.Execute(null);
                    break;
                case 2:
                    Message = ((MessageResponse)response).getOptional();
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
        public AddCourseViewModel(FacultyController facultyController, AccountStore accountStore, INavigationService closeNavigationService)
        {
            _controller = facultyController;
            _account = accountStore;
            CloseCommand = new NavigateCommand(closeNavigationService);
            AddCourseCommand = new GenericCommand(this);
            if (_account.CurrentAccount.Permission != 5)
            {
                Application.Current.Shutdown();
            }
        }

        public override void Execute(object parameter)
        {
            AddCourse();
        }
    }
}