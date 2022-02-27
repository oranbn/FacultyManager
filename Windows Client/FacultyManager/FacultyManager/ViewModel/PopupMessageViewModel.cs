using System.Windows.Input;
using FacultyManager.Commands;
using FacultyManager.Model.Operations.ServerResponse;
using FacultyManager.Service;

namespace FacultyManager.ViewModel
{
    public class PopupMessageViewModel  : NotifiableObject
    {
        public string Message => _message;
        private string _message;
        public ICommand NavigateCommand { get; }
        private bool _error;
        public bool Error => _error;
        public bool Correct => !_error;
        public PopupMessageViewModel(MessageResponse response, INavigationService navigationService)
        {
            switch (response.getOpCode())
            {
                case 1:
                    _error = false;
                    break;
                case 2:
                    _error = true;
                    break;
            }
            _message = response.getOptional();
            NavigateCommand = new NavigateCommand(navigationService);
        }
    }
}