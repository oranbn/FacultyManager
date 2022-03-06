using FacultyManager.Model;
using FacultyManager.Service;
using FacultyManager.Stores;
using FacultyManager.ViewModel;
using System;
using System.Collections.Generic;
using System.Text;
using System.Windows;
using FacultyManager.Model.Operations.ServerResponse;

namespace FacultyManager.Commands
{
    public class GenericCommand : CommandBase
    {
        private readonly NotifiableObject _viewModel;

        public GenericCommand(NotifiableObject viewModel)
        {
            _viewModel = viewModel;
        }

        public override void Execute(object parameter)
        {
            _viewModel.Execute(parameter);
        }
    }
}