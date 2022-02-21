using FacultyManager.Model;

namespace FacultyManager.Stores
{
    public class AccountStore
    {
        private Account _currentAccount;

        public Account CurrentAccount
        {
            get => _currentAccount;
            set
            {
                _currentAccount = value;
            }
        }
        public bool IsLoggedIn => CurrentAccount != null;
        public bool IsNotLoggedIn => CurrentAccount == null;
    }
}