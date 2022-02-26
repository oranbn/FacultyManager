using System.Windows.Input;

namespace FacultyManager.ViewModel
{
    public class PopupMessageViewModel
    {
        public string Message;
        public ICommand NavigateCommand { get; }
    }
}