import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*
import hudson.security.*
import jenkins.security.s2m.AdminWhitelistRule

def env = System.getenv()

println '--> creating initial user ' + env.JENKINS_USER + ' with password ' + env.JENKINS_PASS

def jenkins = Jenkins.getInstance()
jenkins.setSecurityRealm(new HudsonPrivateSecurityRealm(false))
jenkins.setAuthorizationStrategy(new GlobalMatrixAuthorizationStrategy())

def user = jenkins.getSecurityRealm().createAccount(env.JENKINS_USER, env.JENKINS_PASS)
user.save()

jenkins.getAuthorizationStrategy().add(Jenkins.ADMINISTER, env.JENKINS_USER)
jenkins.save()

println '--> enabling slave-to-master access control'
// https://wiki.jenkins.io/display/JENKINS/Slave+To+Master+Access+Control
Jenkins.instance.getInjector().getInstance(AdminWhitelistRule.class)
.setMasterKillSwitch(false)

println '--> setting number of executors to ' + env.JENKINS_EXECUTORS
Jenkins.instance.setNumExecutors(env.JENKINS_EXECUTORS as Integer)

println '--> setting up maven: ' + env.MAVEN_VERSION

a=Jenkins.instance.getExtensionList(hudson.tasks.Maven.DescriptorImpl.class)[0];
b=(a.installations as List);
b.add(new hudson.tasks.Maven.MavenInstallation("MAVEN3", "/home/jenkins/apache-maven/apache-maven-3.2.3", []));
a.installations=b
a.save()

println "--> initial setup done."
