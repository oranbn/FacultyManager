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
        public override void Dispose()
        {
            NavigationBarViewModel.Dispose();
            ContentViewModel.Dispose();

            base.Dispose();
        }

        public override void Execute(object parameter)
        {
            throw new NotImplementedException();
        }
    }
}