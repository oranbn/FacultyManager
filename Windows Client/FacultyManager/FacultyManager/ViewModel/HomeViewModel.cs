using FacultyManager.Commands;
using FacultyManager.Model;
using FacultyManager.Service;
using FacultyManager.Stores;
using System.Windows.Input;
using FacultyManager.ViewModel;

namespace FacultyManager.ViewModel
{
    public class HomeViewModel : NotifiableObject
    {
        public string WelcomeMessage => "Welcome to FacultyManager application.";
        public NavigationBarViewModel NavigationBarViewModel { get; }
        

        public HomeViewModel(NavigationBarViewModel navigationBarViewModel)
        {
            NavigationBarViewModel = navigationBarViewModel;
        }
    }
}