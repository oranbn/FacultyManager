using FacultyManager.Commands;
using FacultyManager.Model;
using FacultyManager.Service;
using FacultyManager.Stores;
using System.Windows.Input;
using FacultyManager.ViewModel;

namespace FacultyManager.ViewModel
{
    public class AccountViewModel : NotifiableObject
    {
        private readonly AccountStore _accountStore;

        public AccountViewModel(AccountStore accountStore)
        {
            _accountStore = accountStore;
        }
    }
}