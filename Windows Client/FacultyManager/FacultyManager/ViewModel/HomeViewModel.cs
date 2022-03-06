using System.Windows;
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
        public bool IsLoggedIn => _accountStore.IsLoggedIn;
        private readonly AccountStore _accountStore;
        private readonly FacultyController _facultyController;
        private readonly CoursesModel _coursesModel;
        public ICommand Test { get; }

        public CoursesModel CoursesModel => _coursesModel;


        public HomeViewModel(FacultyController facultyController, AccountStore accountStore)
        {
            _facultyController = facultyController;
            _accountStore = accountStore;
            Test = new GenericCommand(this);
            _coursesModel = new CoursesModel(facultyController, accountStore);
        }

        public override void Execute(object parameter)
        {
            MessageBox.Show("oran");
        }
    }
}