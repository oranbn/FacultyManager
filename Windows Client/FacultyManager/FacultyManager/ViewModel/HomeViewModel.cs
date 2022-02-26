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
        private readonly AccountStore _accountStore;
        private readonly FacultyController _facultyController;
        

        public HomeViewModel(FacultyController facultyController, AccountStore accountStore)
        {
            _facultyController = facultyController;
            _accountStore = accountStore;
        }
    }
}