using FacultyManager.Commands;
using FacultyManager.Service;
using System.Windows.Input;

namespace FacultyManager.ViewModel
{
    public class RegisterViewModel : NotifiableObject
    {

        public ICommand NavigateLoginCommand { get; }

        public RegisterViewModel(INavigationService<LoginViewModel> loginNavigationService)
        {
            NavigateLoginCommand = new NavigateCommand<LoginViewModel>(loginNavigationService);
        }
    }
}