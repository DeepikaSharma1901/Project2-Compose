import jenkins.model.*
import hudson.security.*
import com.cloudbees.plugins.credentials.*

def j = Jenkins.get()

// Local user database (no signups)
def realm = new HudsonPrivateSecurityRealm(false)
if (realm.getUser("admin") == null)    realm.createAccount("admin", "admin123!")
if (realm.getUser("pipeline") == null) realm.createAccount("pipeline", "pipeline123!")
j.setSecurityRealm(realm)

// Matrix auth: disable anonymous; minimal rights for pipeline user
def strat = new GlobalMatrixAuthorizationStrategy()
strat.add(Jenkins.ADMINISTER, "admin")
strat.add(Jenkins.READ, "pipeline")
strat.add(Item.DISCOVER, "pipeline")
strat.add(Item.READ, "pipeline")
strat.add(Item.BUILD, "pipeline")
j.setAuthorizationStrategy(strat)

j.save()
