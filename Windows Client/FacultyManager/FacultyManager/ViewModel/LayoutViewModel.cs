using System;
using System.Collections.Generic;
using System.Text;

namespace FacultyManager.ViewModel
{
    public class LayoutViewModel : NotifiableObject
    {
        public NavigationBarViewModel NavigationBarViewModel { get; }
        public NotifiableObject ContentViewModel { get; }

        public LayoutViewModel(NavigationBarViewModel navigationBarViewModel, NotifiableObject contentViewModel)
        {
            NavigationBarViewModel = navigationBarViewModel;
            ContentViewModel = contentViewModel;
        }
    }
}