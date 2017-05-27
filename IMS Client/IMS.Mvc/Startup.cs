using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(IMS.Mvc.Startup))]
namespace IMS.Mvc
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
