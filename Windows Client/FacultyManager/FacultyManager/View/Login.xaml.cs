using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using FacultyManager.ViewModel;

namespace FacultyManager.View
{
    /// <summary>
    /// Interaction logic for Login.xaml
    /// </summary>
    public partial class Login : UserControl
    {
        private LoginViewModel viewModel;
        public Login()
        {
            InitializeComponent();
            Focusable = true;
            Loaded += (s, e) => Keyboard.Focus(this);
        }
    }
}