using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TestClient
{
    class Program
    {
        static void Main(string[] args)
        {
            WebReference.BmiService srv = new WebReference.BmiService();
           var res =  srv.CalculateExBmi(new WebReference.measures()
            {
                Tall = 179,
                Weight = 108
            });

            Console.WriteLine(res);
            Console.ReadLine();
        }
    }
}
