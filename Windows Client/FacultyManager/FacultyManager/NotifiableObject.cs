using System;
using System.ComponentModel;

namespace FacultyManager
{
    public abstract class NotifiableObject : INotifyPropertyChanged, IDisposable
    {
        public event PropertyChangedEventHandler PropertyChanged;
        protected void RaisePropertyChanged(string property)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(property));
        }
        public virtual void Dispose() { }
        public abstract void Execute();
    }
}
