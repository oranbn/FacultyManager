using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace FacultyManager.Model
{
    public class FacultyController
    {
        public FacultyController()
        {
            Thread thr = new Thread(GetServerThread);
            thr.Start();
        }
        private void GetServerThread()
        {
            
        }
        internal IEnumerable<CourseModel> GetAllCourses(UserModel user)
        {
            throw new NotImplementedException();
        }

    }
}
