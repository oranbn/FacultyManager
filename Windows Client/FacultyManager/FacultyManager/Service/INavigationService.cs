using FacultyManager.ViewModel;

namespace FacultyManager.Service
{
    public interface INavigationService<TViewModel> where TViewModel : NotifiableObject
    {
        void Navigate();
    }
}