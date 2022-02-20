using FacultyManager.Commands;
using FacultyManager.Stores;
using System.Windows.Input;
using FacultyManager.ViewModel;

namespace FacultyManager.ViewModel
{
    public class RegisterViewModel : NotifiableObject
    {
        public string WelcomeMessage => "Welcome to my application.";

        public ICommand NavigateLoginCommand { get; }

        public RegisterViewModel(NavigationStore navigationStore)
        {
            NavigateLoginCommand = new NavigateCommand<LoginViewModel>(navigationStore, () => new LoginViewModel(navigationStore));
        }
    }
}