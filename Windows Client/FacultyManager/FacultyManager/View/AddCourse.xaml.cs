using System.Windows.Controls;
using System.Windows.Input;

namespace FacultyManager.View
{
    public partial class AddCourse : UserControl
    {
        public AddCourse()
        {
            InitializeComponent();
            Focusable = true;
            Loaded += (s, e) => Keyboard.Focus(this);
        }
    }
}