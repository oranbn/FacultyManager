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
    public class LoginCommand : CommandBase
    {
        private readonly LoginViewModel _viewModel;
        private readonly INavigationService<HomeViewModel> _navigationService;

        public LoginCommand(LoginViewModel viewModel,  INavigationService<HomeViewModel> navigationService)
        {
            _viewModel = viewModel;
            _navigationService = navigationService;
        }

        public override void Execute(object parameter)
        {
            _viewModel.Login();
            // check if login works if so navigate to home page
            _navigationService.Navigate();
        }
    }
}