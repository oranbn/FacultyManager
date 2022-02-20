namespace FacultyManager.Model
{
    public abstract class NotifiableModelObject : NotifiableObject
    {
        public FacultyController Controller { get; private set; }
        protected NotifiableModelObject(FacultyController controller)
        {
            this.Controller = controller;
        }
    }
}
