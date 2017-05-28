using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IMS.Models
{
    public class Mail
    {

        [Display(Name = "Id")]
        public int id { get; set; }

        [Display(Name = "Message")]
        public string message { get; set; }

        [Display(Name = "Name")]
        public string name { get; set; }


        [Display(Name = "To")]
        public string to { get; set; }
    }
}
