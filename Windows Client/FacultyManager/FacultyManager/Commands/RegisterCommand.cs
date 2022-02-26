using FacultyManager.Model;
using FacultyManager.Service;
using FacultyManager.Stores;
using FacultyManager.ViewModel;
using System;
using System.Collections.Generic;
using System.Text;
using System.Windows;

namespace FacultyManager.Commands
{
    public class RegisterCommand : CommandBase
    {
        private readonly RegisterViewModel _viewModel;
        private readonly INavigationService _navigationService;

        public RegisterCommand(RegisterViewModel viewModel,  INavigationService navigationService)
        {
            _viewModel = viewModel;
            _navigationService = navigationService;
        }

        public override void Execute(object parameter)
        {
            _viewModel.Register();
            // check if login works if so navigate to home page
            _navigationService.Navigate();
        }
    }
}