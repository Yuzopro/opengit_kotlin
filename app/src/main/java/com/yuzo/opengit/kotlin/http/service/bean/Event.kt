package com.yuzo.opengit.kotlin.http.service.bean

import com.google.gson.annotations.SerializedName

/**
 * Author: yuzo
 * Date: 2019-10-11
 */
data class Event(
    val `public`: Boolean,
    val actor: Actor,
    val created_at: String,
    val id: String,
    val payload: Payload,
    val repo: Repo,
    val type: Type
)

data class Actor(
    @SerializedName("id")
    val actorId: Int,
    val login: String,
    @SerializedName("display_login")
    val displayLogin: String,
    @SerializedName("gravatar_id")
    val gravatarId: String,
    val url: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)

data class Payload(
    val action: String,
    val comment: Comment,
    val issue: Issue
)

data class Comment(
    val author_association: String,
    val body: String,
    val created_at: String,
    val html_url: String,
    val id: Int,
    val issue_url: String,
    val node_id: String,
    val updated_at: String,
    val url: String,
    val user: User
)

enum class Type {
    WatchEvent,
    ForkEvent,
    PushEvent,
    CreateEvent,
    MemberEvent,
    PublicEvent,
    IssuesEvent,
    IssueCommentEvent,
    CheckRunEvent,
    CheckSuiteEvent,
    CommitCommentEvent,
    DeleteEvent,
    DeploymentEvent,
    DeploymentStatusEvent,
    DownloadEvent,
    FollowEvent,
    ForkApplyEvent,
    GitHubAppAuthorizationEvent,
    GistEvent,
    GollumEvent,
    InstallationEvent,
    InstallationRepositoriesEvent,
    MarketplacePurchaseEvent,
    LabelEvent,
    MembershipEvent,
    MilestoneEvent,
    OrganizationEvent,
    OrgBlockEvent,
    PageBuildEvent,
    ProjectCardEvent,
    ProjectColumnEvent,
    ProjectEvent,
    PullRequestEvent,
    PullRequestReviewEvent,
    PullRequestReviewCommentEvent,
    ReleaseEvent,
    RepositoryEvent,
    RepositoryImportEvent,
    RepositoryVulnerabilityAlertEvent,
    SecurityAdvisoryEvent,
    StatusEvent,
    TeamEvent,
    TeamAddEvent
}