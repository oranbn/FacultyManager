using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;

namespace FacultyManager.View
{
    /// <summary>
    /// Interaction logic for Login.xaml
    /// </summary>
    public partial class Login : Window
    {
/*        private LoginViewModel viewModel;*/
        public Login()
        {
            InitializeComponent();
/*            this.DataContext = new LoginViewModel();
            this.viewModel = (LoginViewModel)DataContext;*/
        }

        private void Login_Click(object sender, RoutedEventArgs e)
        {
            DoLogin();
        }
        private void DoLogin()
        {
            /*UserModel u = viewModel.Login();
            if (u != null)
            {
                KanbanView kanban = new KanbanView(u);
                kanban.Show();
                this.Close();
            }*/
        }
        private void Register_Click(object sender, RoutedEventArgs e)
        {
            /*viewModel.Register();*/
        }

        private void EmailTextBox_TextChanged(object sender, System.Windows.Controls.TextChangedEventArgs e)
        {
            /*viewModel.ClearMessage();*/

        }

        private void PasswordTextBox_TextChanged(object sender, System.Windows.Controls.TextChangedEventArgs e)
        {
           /* viewModel.ClearMessage();*/
        }

        private void TextBox_KeyDown(object sender, System.Windows.Input.KeyEventArgs e)
        {
            if (e.Key == Key.Return)
            {
                DoLogin();
            }

        }
    }
}
