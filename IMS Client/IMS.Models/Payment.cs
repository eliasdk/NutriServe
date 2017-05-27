using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IMS.Models
{
   public class Payment
    {
        [Display(Name = "Card")]
        public string card { get; set; }

        [Display(Name = "Create Time")]
        public string createTime { get; set; }

        [Display(Name = "Id")]
        public string id { get; set; }

        [Display(Name = "Method")]
        public string method { get; set; }

        [Display(Name = "State")]
        public int state { get; set; }
        public string type { get; set; }

        [Display(Name = "Update Time")]
        public string updateTime { get; set; }
    }
}
