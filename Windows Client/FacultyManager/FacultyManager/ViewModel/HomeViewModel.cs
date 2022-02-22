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
  
        

        public HomeViewModel(AccountStore accountStore)
        {
            _accountStore = accountStore;
        }
    }
}